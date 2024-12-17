import javax.swing.*; // Импорт пакета javax.swing для использования графических компонентов Swing
import java.awt.*; // Импорт пакета java.awt для использования классов для работы с графическим интерфейсом
import java.awt.event.ActionEvent; // Импорт класса ActionEvent из пакета java.awt.event для обработки событий
import java.awt.event.ActionListener; // Импорт интерфейса ActionListener из пакета java.awt.event для создания прослушивателя событий

public class CalculatorApp extends JFrame { // Определение класса CalculatorApp, который расширяет класс JFrame для создания главного окна приложения

    private JTextField num1Field; // Поле для ввода первого числа
    private JTextField num2Field; // Поле для ввода второго числа
    private JLabel resultLabel; // Метка для вывода результата операции
    private JComboBox<String> operationComboBox; // Перечисляемый список для выбора операции

    public CalculatorApp() { // Конструктор класса CalculatorApp
        setTitle("Калькулятор"); // Установка заголовка окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Установка операции закрытия приложения при закрытии окна
        setSize(300, 200); // Установка размера окна
        setLocationRelativeTo(null); // Центрирование окна по центру экрана

        JPanel panel = new JPanel(); // Создание панели для размещения компонентов
        panel.setLayout(new GridLayout(5, 2, 5, 5)); // Установка менеджера компоновки для панели

        panel.add(new JLabel("Первое число:")); // Добавление метки "Первое число" на панель
        num1Field = new JTextField(); // Создание текстового поля для ввода первого числа
        panel.add(num1Field); // Добавление текстового поля на панель

        panel.add(new JLabel("Второе число:")); // Добавление метки "Второе число" на панель
        num2Field = new JTextField(); // Создание текстового поля для ввода второго числа
        panel.add(num2Field); // Добавление текстового поля на панель

        panel.add(new JLabel("Выберите операцию:")); // Добавление метки "Выберите операцию" на панель
        operationComboBox = new JComboBox<>(new String[]{"Сложение", "Вычитание", "Умножение", "Деление", "Остаток от деления"}); // Создание перечисляемого списка с операциями
        panel.add(operationComboBox); // Добавление перечисляемого списка на панель

        JButton calculateButton = new JButton("Вычислить"); // Создание кнопки "Вычислить"
        calculateButton.addActionListener(new ActionListener() { // Добавление прослушивателя событий для кнопки "Вычислить"
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate(); // Вызов метода calculate() при нажатии на кнопку
            }
        });
        panel.add(calculateButton); // Добавление кнопки на панель

        panel.add(new JLabel("Результат:")); // Добавление метки "Результат" на панель
        resultLabel = new JLabel(); // Создание метки для вывода результата операции
        panel.add(resultLabel); // Добавление метки на панель

        add(panel); // Добавление панели на главное окно приложения
    }

    private void calculate() { // Метод для выполнения вычислений
        try {
            int num1 = Integer.parseInt(num1Field.getText()); // Получение первого числа из текстового поля
            int num2 = Integer.parseInt(num2Field.getText()); // Получение второго числа из текстового поля

            SmallInteger smallNum1 = new SmallInteger(num1); // Создание объекта SmallInteger для первого числа
            SmallInteger smallNum2 = new SmallInteger(num2); // Создание объекта SmallInteger для второго числа

            int operationIndex = operationComboBox.getSelectedIndex(); // Получение индекса выбранной операции из перечисляемого списка
            SmallInteger result; // Объявление переменной для хранения результата операции
            switch (operationIndex) { // Выбор операции в зависимости от индекса
                case 0:
                    result = smallNum1.add(smallNum2); // Сложение
                    break;
                case 1:
                    result = smallNum1.subtract(smallNum2); // Вычитание
                    break;
                case 2:
                    result = smallNum1.multiply(smallNum2); // Умножение
                    break;
                case 3:
                    result = smallNum1.divide(smallNum2); // Деление
                    break;
                case 4:
                    result = smallNum1.modulo(smallNum2); // Остаток от деления
                    break;
                default:
                    throw new IllegalArgumentException("Неверная операция"); // Генерация исключения при неверной операции
            }

            resultLabel.setText(result.toString()); // Вывод результата операции на метку
        } catch (NumberFormatException ex) { // Обработка исключения при неверном формате ввода
            resultLabel.setText("Ошибка: Введите целые числа"); // Вывод сообщения об ошибке на метку
        } catch (IllegalArgumentException | ArithmeticException ex) { // Обработка исключений при некорректных вычислениях
            resultLabel.setText("Ошибка: " + ex.getMessage()); // Вывод сообщения об ошибке на метку
        }
    }

    public static void main(String[] args) { // Метод main для запуска приложения
        SwingUtilities.invokeLater(new Runnable() { // Использование invokeLater для выполнения в потоке обработки событий
            @Override
            public void run() {
                new CalculatorApp().setVisible(true); // Создание экземпляра приложения и отображение его на экране
            }
        });
    }
}
