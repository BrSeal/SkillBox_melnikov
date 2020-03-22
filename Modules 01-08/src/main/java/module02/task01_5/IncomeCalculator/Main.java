package module02.task01_5.IncomeCalculator;

import java.util.Scanner;

/**
 * Программа получает на входе сумму доходов компании и исходя из нее, выводит посчитанную ЗП менеджера, общую сумму
 * налогов и может ли кампания инвестировать.
 * Чтобы выйти из программы нужно ввести не число.
 */
public class Main //объявляем класс Main
{

    private static int minIncome = 200000;// объявляем статическую целочисленную переменную и присваиваем ей значение
    private static int maxIncome = 900000;// объявляем статическую целочисленную переменную и присваиваем ей значение

    private static int officeRentCharge = 140000;// объявляем статическую целочисленную переменную и присваиваем ей значение
    private static int telephonyCharge = 12000;// объявляем статическую целочисленную переменную и присваиваем ей значение
    private static int internetAccessCharge = 7200;// объявляем статическую целочисленную переменную и присваиваем ей значение

    private static int assistantSalary = 45000;// объявляем статическую целочисленную переменную и присваиваем ей значение
    private static int financeManagerSalary = 90000;// объявляем статическую целочисленную переменную и присваиваем ей значение

    private static double mainTaxPercent = 0.24;// объявляем статическую дробную переменную и присваиваем ей значение
    private static double managerPercent = 0.15;// объявляем статическую дробную переменную и присваиваем ей значение

    private static double minInvestmentsAmount = 100000;// объявляем статическую дробную переменную и присваиваем ей значение

    public static void main(String[] args) //точка запуска программы
    {
        while (true) //бесконечный цикл, так как условие всегда true и в теле нет
        // точки выхода и выйти можно только через исключение в строке 28
        {
            System.out.println("Введите сумму доходов компании за месяц " +
                    "(от 200 до 900 тысяч рублей): "); // выводим надпись в консоль. Знаки пробела, табуляции и переноса строки
            // можно ставить сколько угодно
            int income = (new Scanner(System.in)).nextInt(); /*объявляем локальную переменную и кладем в нее число,
                                                              которое введет пользователь. Если будет введено
                                                              не целое число, то будет выброшено исключение*/

            if (!checkIncomeRange(income)) {/*проверяем условие НЕ checkIncomeRange(income) (запускаем метод,
                                             на выходе булево значение)*/
                continue;                  //если истина, то переходим на следующую итерацию цикла
            }

            double managerSalary = income * managerPercent;/*объявляем дробную переменную и присваиваем ей значение ЗП
                                                             менеджера, которая является процентом с дохода (int*double=>double)*/
            double pureIncome = income - managerSalary -
                    calculateFixedCharges();/*объявляем дробную переменную и присваиваем ей результат вычисления
                                         (int-double=>double, double-int=>double) чистого дохода
                                         (полный доход за вычетом ЗП менеджера и постоянных затрат)*/
            double taxAmount = mainTaxPercent * pureIncome;/*объявляем дробную переменную и присваиваем ей результат
                                                            вычисления суммы налога с чистого дохода*/
            double pureIncomeAfterTax = pureIncome - taxAmount;/*объявляем дробную переменную и присваиваем
                                                                 ей результат вычисления
                                                                 */

            boolean canMakeInvestments = pureIncomeAfterTax >=
                    minInvestmentsAmount;/*объявляем булеву переменную, которая "решает" можно ли фирме инвестировать и
                                       присваиваем ей результат вычисления true если pureIncomeAfterTax больше или
                                       равно minInvestmentsAmount и false если наоборот*/

            System.out.println("Зарплата менеджера: " + managerSalary); //выводим информацию в консоль
            System.out.println("Общая сумма налогов: " +
                    (taxAmount > 0 ? taxAmount : 0));//выводим информацию в консоль
            System.out.println("Компания может инвестировать: " +
                    (canMakeInvestments ? "да" : "нет"));/*выводим информацию в консоль и, используя тернарный оператор
                                                       если фирма может инвестировать (canMakeInvestments=true)
                                                       выводим да, иначе - нет*/
            if (pureIncome < 0) { //проверяем условие
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!"); //выводим строку в консоль
            }
        }
    }

    private static boolean checkIncomeRange(int income)//объявляем статический метод c параметром int, возвращающий boolean
    {
        if (income < minIncome) //проверяем условие, если  true, то выполняем соответствующй блок кода
        {
            System.out.println("Доход меньше нижней границы"); //выводим строку в консоль
            return false;// вернуть ЛОЖЬ
        }
        if (income > maxIncome) {
            System.out.println("Доход выше верхней границы");//выводим строку в консоль
            return false;// вернуть ЛОЖЬ
        }
        return true;// вернуть ИСТИНУ
    }

    private static int calculateFixedCharges() //объявляем статический метод без параметров, возвращающий int
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary; // возвращаем сумму переменных
    }
}
