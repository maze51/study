package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CalculatorTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPrefSize(520, 280);
		root.setPadding(new Insets(10));
		
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setPadding(new Insets(5));
		TextField tf = new TextField("0");
		tf.setPrefHeight(50);
		tf.setFont(new Font(20));
		tf.setAlignment(Pos.BOTTOM_RIGHT);
		
		Button blankLarge = new Button();
		blankLarge.setPrefSize(244, 30);
		Button blankSmall = new Button();
		blankSmall.setPrefSize(45, 30);
		Button intb = new Button("Int");
		intb.setPrefSize(45, 30);
		Button dms = new Button("dms");
		dms.setPrefSize(45, 30);
		Button PI = new Button("π");
		PI.setPrefSize(45, 30);
		Button fe = new Button("F-E");
		fe.setPrefSize(45, 30);
		
		grid.add(blankLarge, 0, 0, 5, 1);
		grid.add(blankSmall, 0, 1);
		grid.add(intb, 0, 2);
		grid.add(dms, 0, 3);
		grid.add(PI, 0, 4);
		grid.add(fe, 0, 5);
		
		Button inv = new Button("Inv");
		inv.setPrefSize(45, 30);
		Button sinh = new Button("sinh");
		sinh.setPrefSize(45, 30);
		Button cosh = new Button("cosh");
		cosh.setPrefSize(45, 30);
		Button tanh = new Button("tanh");
		tanh.setPrefSize(45, 30);
		Button exp = new Button("Exp");
		exp.setPrefSize(45, 30);
		
		grid.add(inv, 1, 1);
		grid.add(sinh, 1, 2);
		grid.add(cosh, 1, 3);
		grid.add(tanh, 1, 4);
		grid.add(exp, 1, 5);
		
		Button in = new Button("In");
		in.setPrefSize(45, 30);
		Button sin = new Button("sin");
		sin.setPrefSize(45, 30);
		Button cos = new Button("cos");
		cos.setPrefSize(45, 30);
		Button tan = new Button("tan");
		tan.setPrefSize(45, 30);
		Button mod = new Button("Mod");
		mod.setPrefSize(45, 30);
		
		grid.add(in, 2, 1);
		grid.add(sin, 2, 2);
		grid.add(cos, 2, 3);
		grid.add(tan, 2, 4);
		grid.add(mod, 2, 5);
		
		Button lBracket = new Button("(");
		lBracket.setPrefSize(45, 30);
		Button x2 = new Button("x2");
		x2.setPrefSize(45, 30);
		Button xy = new Button("xy");
		xy.setPrefSize(45, 30);
		Button x3 = new Button("x3");
		x3.setPrefSize(45, 30);
		Button log = new Button("log");
		log.setPrefSize(45, 30);
		
		grid.add(lBracket, 3, 1);
		grid.add(x2, 3, 2);
		grid.add(xy, 3, 3);
		grid.add(x3, 3, 4);
		grid.add(log, 3, 5);
		
		Button rBracket = new Button(")");
		rBracket.setPrefSize(45, 30);
		Button exMark = new Button("n!");
		exMark.setPrefSize(45, 30);
		Button xRooty = new Button("y√x");
		xRooty.setPrefSize(45, 30);
		Button xRoot3 = new Button("3√x");
		xRoot3.setPrefSize(45, 30);
		Button x10 = new Button("10x");
		x10.setPrefSize(45, 30);
		
		grid.add(rBracket, 4, 1);
		grid.add(exMark, 4, 2);
		grid.add(xRooty, 4, 3);
		grid.add(xRoot3, 4, 4);
		grid.add(x10, 4, 5);
		
		Button mc = new Button("MC");
		mc.setPrefSize(45, 30);
		Button arrow = new Button("<--");
		arrow.setPrefSize(45, 30);
		Button seven = new Button("7");
		seven.setPrefSize(45, 30);
		Button four = new Button("4");
		four.setPrefSize(45, 30);
		Button one = new Button("1");
		one.setPrefSize(45, 30);
		Button zero = new Button("0");
		zero.setPrefSize(95, 30);
		
		grid.add(mc, 6, 0);
		grid.add(arrow, 6, 1);
		grid.add(seven, 6, 2);
		grid.add(four, 6, 3);
		grid.add(one, 6, 4);
		grid.add(zero, 6, 5, 2, 1);
		
		Button mr = new Button("MR");
		mr.setPrefSize(45, 30);
		Button ce = new Button("CE");
		ce.setPrefSize(45, 30);
		Button eight = new Button("8");
		eight.setPrefSize(45, 30);
		Button five = new Button("5");
		five.setPrefSize(45, 30);
		Button two = new Button("2");
		two.setPrefSize(45, 30);
		
		grid.add(mr, 7, 0);
		grid.add(ce, 7, 1);
		grid.add(eight, 7, 2);
		grid.add(five, 7, 3);
		grid.add(two, 7, 4);
		
		Button ms = new Button("MS");
		ms.setPrefSize(45, 30);
		Button c = new Button("C");
		c.setPrefSize(45, 30);
		Button nine = new Button("9");
		nine.setPrefSize(45, 30);
		Button six = new Button("6");
		six.setPrefSize(45, 30);
		Button three = new Button("3");
		three.setPrefSize(45, 30);
		Button dot = new Button(".");
		dot.setPrefSize(45, 30);
		
		grid.add(ms, 8, 0);
		grid.add(c, 8, 1);
		grid.add(nine, 8, 2);
		grid.add(six, 8, 3);
		grid.add(three, 8, 4);
		grid.add(dot, 8, 5);
		
		Button mPlus = new Button("M+");
		mPlus.setPrefSize(45, 30);
		Button pm = new Button("±");
		pm.setPrefSize(45, 30);
		Button divide = new Button("/");
		divide.setPrefSize(45, 30);
		Button multi = new Button("*");
		multi.setPrefSize(45, 30);
		Button minus = new Button("-");
		minus.setPrefSize(45, 30);
		Button plus = new Button("+");
		plus.setPrefSize(45, 30);
		
		grid.add(mPlus, 9, 0);
		grid.add(pm, 9, 1);
		grid.add(divide, 9, 2);
		grid.add(multi, 9, 3);
		grid.add(minus, 9, 4);
		grid.add(plus, 9, 5);
		
		Button mMinus = new Button("M-");
		mMinus.setPrefSize(45, 30);
		Button root1 = new Button("√");
		root1.setPrefSize(45, 30);
		Button percent = new Button("%");
		percent.setPrefSize(45, 30);
		Button oneX = new Button("1/x");
		oneX.setPrefSize(45, 30);
		Button equal = new Button("=");
		equal.setPrefSize(45, 65);
		
		grid.add(mMinus, 10, 0);
		grid.add(root1, 10, 1);
		grid.add(percent, 10, 2);
		grid.add(oneX, 10, 3);
		grid.add(equal, 10, 4, 1, 2);
		
		root.setTop(tf);
		root.setCenter(grid);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("계산기 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
