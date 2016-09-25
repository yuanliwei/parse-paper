package com.ylw.parsepaper.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ylw.parsepaper.utils.PropUtils;

public class MainWindow {

	public static void main(String[] args) {
		PropUtils.load();
		/**
		 * 第一步创建Display,对应操作系统的控件，使用完必须释放
		 */
		Display display = new Display();
		/**
		 * 第二部创建shell
		 * 
		 * @style
		 */
		Shell shell = new Shell(display);
		shell.setText("第一个shell窗口");
		/**
		 * 第三部指定容器的布局类型
		 */
		GridLayout gl = new GridLayout(2, false);
		gl.marginBottom = 20;
		gl.marginTop = 10;
		gl.marginLeft = 30;
		gl.marginRight = 30;
		gl.verticalSpacing = 20;
		gl.horizontalSpacing = 20;
		shell.setLayout(gl);
		/****
		 * 第四步创建容器里的控件，并指定摆放位置
		 */
		Label label1 = new Label(shell, SWT.NONE);
		label1.setText("姓名");
		label1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		/**
		 * 第一个参数是操作系统资源 第二参数是字体样式 第三参数是字的高度（字号大小） 第4个参数是字显示样式
		 */
		Font labelFont = new Font(display, "微软雅黑", 20, SWT.NONE);

		Text text1 = new Text(shell, SWT.BORDER);
		text1.setFont(labelFont);
		text1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

		Label label2 = new Label(shell, SWT.NONE);
		label2.setText("密码");

		Color foreColor = new Color(display, 255, 0, 0);
		label2.setForeground(foreColor);

		Text text2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text2.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		Label label3 = new Label(shell, SWT.NONE);
		label3.setText("下拉：");
		Combo combo = new Combo(shell, SWT.NONE);
		for (int i = 1; i <= 9; i++) {
			combo.add("下拉选项" + i);
		}
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		Browser browser = new Browser(shell, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		browser.setJavascriptEnabled(true);
		String htmlPath = "file:///C:/Users/ylw/Desktop/tempout/handled2.html";
		browser.setUrl(htmlPath);

		Button btn = new Button(shell, SWT.NONE);
		btn.setText("提交");
		btn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
//				htmlPath = PropUtils.get("test_html_path").replace("\\", "\\\\");
//				htmlPath = "file:///C:/Users/ylw/Desktop/tempout/handled2.html";
//				browser.evaluate("alert(window.getSelectionText())");
				browser.setUrl(htmlPath);
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();

	}

}
