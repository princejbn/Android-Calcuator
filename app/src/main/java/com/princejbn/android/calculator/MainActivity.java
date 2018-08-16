package com.princejbn.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //assigning values to mathematical operations such as addition,multiplication,division&subtraction
    //so that they can be stored in integer array
    static final int plus = -999995;
    static final int minus = -999996;
    static final int multiply = -999997;
    static final int divide = -999998;
    static final int modulus = -999999;
    static int flag = 0;
    //expression array store the expression which has to be evaluated
    //exp works as index
    int[] expression = new int[10];
    int exp = -1;

    //disp array stores the number as it is presses,this is used for displaying on the screen
    //top works as index
    char[] disp = new char[10];
    int top = -1;

    //screen is the text view which displays the expression given by user
    //res_screen displays the answer of the expression
    TextView screen;
    TextView res_screen;


    public void display() {
        //this function is used to display the expression on the screen

        //if there is expression
        if (top >= 0)
            screen.setText(disp, 0, top + 1);

            //if no expression is there
        else
            screen.setText("");
    }

    void display_res(int num) {
        //this function displays the result on the result screen
        res_screen.setText("" + num);
    }


    public void calculate() {
        //it is used to evaluate the expression
        //it is called whenever user presses equals to sign

        //if no operand,return
        if (exp < 0)
            return;

        //if only one operand is there,display it
        if (exp == 0) {
            display_res(expression[exp]);
            return;
        }

        //if one operand is there along with an operator,display the operand
        if (exp == 1) {
            display_res(expression[exp - 1]);
            return;
        }

        //until there are two expression and an operator
        while (exp >= 2) {
            int op2 = expression[exp--];//first one is operand2
            int op = expression[exp--];//second one is operator
            int op1 = expression[exp--];//third one is operand1
            int res = simplify(op1, op, op2);//simplify function gives the result of the single expression
            expression[++exp] = res;//save the result back to array
        }
        display_res(expression[exp]);//display the results when calculation is done
        flag = 1;
    }


    public int simplify(int op1, int op, int op2) {

        //it takes one simple expression as one operator and two expression and evaluates it
        //op1 and op2 are expression and op is operator
        //it returns the result
        int res = 0;
        switch (op) {
            case plus:
                res = op1 + op2;
                break;
            case minus:
                res = op1 - op2;
                break;
            case multiply:
                res = op1 * op2;
                break;
            case divide:
                res = op1 / op2;
                break;
            case modulus:
                res = op1 % op2;
                break;
            default:
                res = 0;
                break;
        }
        return res;
    }

    public void add(char ele) {
        //it adds the char to the disp array

        //if array is full,flush it by making top equals -1
        if (top == 9)
            top = -1;
        if (flag == 1) {
            top = -1;
        }

        //otherwise add the char to array
        disp[++top] = ele;
    }

    public void add(int ele) {

        //this is used to add the number to expression array

        //if array is full,flush it
        if (exp == 9)
            exp = -1;

        if (flag == 1) {
            exp = -1;
            flag = 0;
        }

        if ((exp > 0 && expression[exp] < 0 && ele < 0) || (exp == -1 && ele < 0)) {
            top--;
            return;
        }

        //otherwise add the number to array
        expression[++exp] = ele;

        //check if there is two or more digit number like 22,100,etc.
        //if there is then insert the suitable number to array
        if (exp >= 1 && expression[exp - 1] >= 0 && expression[exp] <= 9) {
            if (expression[exp] >= 0 && expression[exp] <= 9) {
                expression[exp - 1] = expression[exp - 1] * 10 + expression[exp];
                exp--;
            }
        }
    }

    public void delete() {
        //it deletes the number from the expression array
        if (exp >= 0) {
            if (expression[exp] > 9) {
                expression[exp] = expression[exp] / 10;
                return;
            }
            exp--;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res_screen = (TextView) findViewById(R.id.result_screen);
        screen = (TextView) findViewById(R.id.expression_screen);
        TextView resScreen = (TextView) findViewById(R.id.result_screen);
        screen.setTextSize(45);
        screen.setTextColor(R.color.colorPrimaryDark);
        screen.setBackgroundColor(R.color.colorAccent);
        resScreen.setBackgroundColor(R.color.colorAccent);
        TextView zero = (TextView) findViewById(R.id.zero);
        zero.setOnClickListener(this);

        //all the views are now defined and associated with their respective listeners to act suitably on  clicked
        TextView one = (TextView) findViewById(R.id.one);
        one.setOnClickListener(this);

        TextView two = (TextView) findViewById(R.id.two);
        two.setOnClickListener(this);

        TextView three = (TextView) findViewById(R.id.three);
        three.setOnClickListener(this);

        TextView four = (TextView) findViewById(R.id.four);
        four.setOnClickListener(this);

        TextView five = (TextView) findViewById(R.id.five);
        five.setOnClickListener(this);

        TextView six = (TextView) findViewById(R.id.six);
        six.setOnClickListener(this);

        TextView seven = (TextView) findViewById(R.id.seven);
        seven.setOnClickListener(this);

        TextView eight = (TextView) findViewById(R.id.eight);
        eight.setOnClickListener(this);

        TextView nine = (TextView) findViewById(R.id.nine);
        nine.setOnClickListener(this);

        TextView plus = (TextView) findViewById(R.id.plus);
        plus.setOnClickListener(this);

        TextView minus = (TextView) findViewById(R.id.minus);
        minus.setOnClickListener(this);

        TextView multiply = (TextView) findViewById(R.id.multiply);
        multiply.setOnClickListener(this);

        TextView divide = (TextView) findViewById(R.id.divide);
        divide.setOnClickListener(this);

        TextView modulus = (TextView) findViewById(R.id.modulus);
        modulus.setOnClickListener(this);

        TextView delete = (TextView) findViewById(R.id.delete);
        delete.setOnClickListener(this);


        TextView equals = (TextView) findViewById(R.id.equals);
        equals.setOnClickListener(this);

        TextView clear = (TextView) findViewById(R.id.clear);
        clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //respective actions to be performed when the numbers are clicked ...
        switch (view.getId()) {
            case R.id.zero:
                add('0');
                add(0);
                display();
                break;

            case R.id.one:
                add('1');
                add(1);
                display();
                break;
            case R.id.two:
                add('2');
                add(2);
                display();
                break;
            case R.id.three:
                add('3');
                add(3);
                display();
                break;

            case R.id.four:
                add('4');
                add(4);
                display();
                break;

            case R.id.five:
                add('5');
                add(5);
                display();
                break;

            case R.id.six:
                add('6');
                add(6);
                display();
                break;

            case R.id.seven:
                add('7');
                add(7);
                display();
                break;

            case R.id.eight:
                add('8');
                add(8);
                display();
                break;


            case R.id.nine:
                add('9');
                add(9);
                display();
                break;

            case R.id.plus:
                add('+');
                add(plus);
                display();
                break;

            case R.id.minus:
                add('-');
                add(minus);
                display();
                break;

            case R.id.multiply:
                add('*');
                add(multiply);
                display();
                break;

            case R.id.divide:
                add('/');
                add(divide);
                display();
                break;

            case R.id.delete:
                if (top >= 0)
                    top--;
                delete();
                display();
                break;

            case R.id.clear:
                top = -1;
                exp = -1;
                screen.setText("");
                res_screen.setText("");
                break;

            case R.id.modulus:
                add('%');
                add(modulus);
                display();
                break;

            case R.id.equals:
                calculate();
                break;
        }
    }
}
