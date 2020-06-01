$(function(){
    const appendTask = function(task){
        var taskCode = '<a href="#" class="task-link" data-id='+task.id
        +'>' + task.data + '</a><br>';
        $('#task-list')
            .append('<li id='+task.id+'>' + taskCode + '</li>');
    };

    $('#show-task-form').click(function(){
        $('#save-task').css('display','block');
        $('#edit-task').css('display','none');
        $('#task-form').css('display', 'flex');
    });

    //Closing adding form
    $('#task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //save task
    $('#save-task').click(function(){
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/add',
            data: data,
            success: function(response){
                $('#task-form').css('display', 'none');
                 var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
            }
        });
        return false;
    });

    //edit task
    $('#edit-task').click(function(){
            var data = $('#task-form form').serialize();
            var taskId=this.getAttribute('task-id');
            $.ajax({
                method: "PUT",
                url: '/tasks/edit/'+taskId,
                data: data,
                success: function(response){
                    var infoDivSelector='li[id='+taskId+'] div';
                    if($(infoDivSelector).length>0){
                        $(infoDivSelector).remove();
                    }
                    $('.task-link[data-id='+taskId+']').text(response);
                }
            });
            $('#task-form').css('display','none');
            return false;
        });

    //show task info (priority etc.)
    $(document).on( 'click', '.task-link', function(){
        var link=$(this);
        var taskId=link.data('id');
        var infoDivSelector='li[id='+taskId+'] div';
        if($(infoDivSelector).length>0){
            $(infoDivSelector).remove();
        }
        else{
            $.ajax({
                method: "GET",
                url: '/tasks/' + taskId,
                success: function(response){
                    var isCompleted=response.completed?'Выполнено':'Не выполнено';
                    var code =
                        '<div>'+
                        '<br>Приоритет :' + response.priority +
                        '<br>'+
                        isCompleted+
                        '<br>'+
                        '<button id="edit-task">Изменить</button>'+
                        '<button id="delete-task">Удалить</button>'+
                        '</div>';
                    link.parent().append(code);
                },
                error: function(response){
                    if(response.status == 404) {
                        alert('Нет такой задачи!');
                    }
                }
            });
            return false;
        }
    });

    //delete task
    $('ul').on('click','button[id=delete-task]',function(){
        var taskId=this.parentNode.parentNode.id;
        $.ajax({
            method: "DELETE",
            url: '/tasks/delete/'+taskId,
            success: function(){
                $('li[id='+taskId+']').remove();
            },
            error: function(response){
                if(response.status == 404) {
                    alert('Ошибка удаления задачи!');
                }
            }
        });
        return false;
    });

    //edit task
    $('ul').on('click','button[id=edit-task]',function(){
    var parentId=this.parentNode.parentNode.id;
    $('#edit-task').attr('task-id', parentId);
    $('#save-task').css('display','none');
    $('#edit-task').css('display','block');
    $('#task-form').css('display', 'flex');
    });
});