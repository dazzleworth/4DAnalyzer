import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.util.Calendar;
import java.text.*;

public class DrawDatesSelectionDialog extends Dialog
{
	public static final Calendar calendar = Calendar.getInstance();

	public DrawDatesSelectionDialog(Shell parent, String title) {
		this(parent, title, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	public DrawDatesSelectionDialog(Shell parent, String title, int style) {
		super(parent, style);
		setText(title);
	}

	private void addControls(Shell shell) {
		/*FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		layout.marginWidth = 5;
		layout.marginHeight = 5;*/

		/*RowLayout layout = new RowLayout();
		layout.wrap = false;
		layout.pack = false;
		layout.marginLeft = 30;
		layout.marginTop = 5;
		layout.type = SWT.VERTICAL;
		layout.marginRight = 15;
		layout.marginBottom = 5;
		layout.justify = true;*/

		shell.setLayout(new GridLayout(2, true));

		int curYear = calendar.get(Calendar.YEAR);

		Combo cboYear = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);

		for(int i = 10; i > 0; i--){	//backdate how many years?
			cboYear.add(String.valueOf(curYear));
			curYear--;
		}
		GridData data = new GridData(SWT.CENTER, SWT.FILL, true, true);
		data.horizontalSpan = 2;
		cboYear.setLayoutData(data);
		cboYear.select(0);


		Combo cboMonth = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);

		DateFormatSymbols dfs = new DateFormatSymbols();

		String[] months = dfs.getMonths();

		int curMonth = calendar.get(Calendar.MONTH);

		for(int i = curMonth - 1; i >= 0; i--) {
			cboMonth.add(months[i]);
		}

		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		cboMonth.setLayoutData(data);
		cboMonth.select(0);

		StringBuffer labeldat = new StringBuffer("Today is : ");


		labeldat.append(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(calendar.getTime()));

		Display display = shell.getDisplay();

		TextStyle datHighlight = new TextStyle(new Font(display, "MS Mincho", 20, SWT.ITALIC), display.getSystemColor(SWT.COLOR_GREEN), null);

		TextLayout DateLabel = new TextLayout(display);

		DateLabel.setStyle(datHighlight, 10, labeldat.length());


		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("OK");
		data = new GridData(GridData.FILL_HORIZONTAL);
		ok.setLayoutData(data);
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//input = text.getText();
				//shell.close();
			}
		});
		

    // Create the cancel button and add a handler
    // so that pressing it will set input to null
    Button cancel = new Button(shell, SWT.PUSH);
    cancel.setText("Cancel");
    data = new GridData(GridData.FILL_HORIZONTAL);
    cancel.setLayoutData(data);
    cancel.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        //input = null;
        //shell.close();
      }
    });
	}

	public void show() {
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		addControls(shell);
		shell.pack();
		shell.open();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}


	}


	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		//shell.setSize(300, 400);
		//shell.open();
		DrawDatesSelectionDialog drawdates = new DrawDatesSelectionDialog(shell, "Select month to analyze");
		drawdates.show();
		//shell.pack();
		shell.open ();
		/*while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}*/
		display.dispose ();
	}
}
