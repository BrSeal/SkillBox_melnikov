$(function(){

 const appendTask = function(task){
        var taskCode = '<a href="#" class="task-link" isOpened="false" data-id='+task.id +
            '">' + task.data + '</a><br>';
        $('#task-list')
            .append('<li>' + taskCode + '</li>');
    };

    $('#show-task-form').click(function(){
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
                success: function(response)
                {
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

    //show task info (priority etc.)
    $(document).on('click', '.task-link', function(){

     var link=$(this);
     var taskId=link.data('id');
     var divName='task-data-'+taskId;
     if($('#'+divName).length>0){
         $('#'+divName).remove();
         }
     else{
         $.ajax({
                    method: "GET",
                    url: '/tasks/' + taskId,
                    success: function(response)
                    {
                        var isCompleted=response.completed?'Выполнено':'Не выполнено';
                        var code =
                        '<div id="'+divName+'">'+
                        '<br>Приоритет :' + response.priority +
                        '<br>'+
                        isCompleted+
                        '<br>'+
                        '<button id="edit-task" taskId="'+taskId+'">Изменить</button>'+
                        '<button id="delete-task" taskId="'+taskId+'">Удалить</button>'+
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

    $('#delete-task').click(function(){
    });
});