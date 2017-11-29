import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


public class SearchDialog extends Dialog
{
	private org.joda.time.DateTime dateFrom;
	private org.joda.time.DateTime dateTo;
	private String searchStr;

	public SearchDialog(Shell parent, String title) {
		this(parent, title,SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	public SearchDialog(Shell parent, String title, int style) {
		super(parent, style);
		setText(title);
	}

	public org.joda.time.DateTime getDateFrom() {
		return dateFrom;
	}

	public org.joda.time.DateTime getDateTo() {
		return dateTo;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void show() {
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		shell.setSize(300, 400);
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

	private void addControls(final Shell shell) {
		shell.setLayout (new GridLayout (1, false));

		Composite dateBoxes = new Composite(shell, SWT.NONE);
		dateBoxes.setLayout(new RowLayout());

		Label lblFrom = new Label(dateBoxes, SWT.HORIZONTAL | SWT.SHADOW_OUT);
		lblFrom.setText("From :");

		final org.eclipse.swt.widgets.DateTime dFrom = new org.eclipse.swt.widgets.DateTime (dateBoxes, SWT.DATE | SWT.MEDIUM);

		//dateFrom.setEnabled(false);

		Button btnselFrom = new Button (dateBoxes, SWT.PUSH);
		btnselFrom.setText("...");
		btnselFrom.addSelectionListener (new SelectionAdapter () {
        		public void widgetSelected (SelectionEvent e) {
				org.joda.time.DateTime dt = showCalPopup(shell);
				DateUtils.updateSWTwithJoda(dFrom, dt);
        		}
      		});

		Label lblTo = new Label(dateBoxes, SWT.HORIZONTAL | SWT.SHADOW_OUT);
		lblTo.setText("To :");

		final org.eclipse.swt.widgets.DateTime dTo = new org.eclipse.swt.widgets.DateTime (dateBoxes, SWT.DATE | SWT.MEDIUM);
		//dateTo.setEnabled(false);

		Button btnselTo = new Button (dateBoxes, SWT.PUSH);
		btnselTo.setText("...");
		btnselTo.addSelectionListener (new SelectionAdapter () {
        		public void widgetSelected (SelectionEvent e) {
				org.joda.time.DateTime dt = showCalPopup(shell);
				DateUtils.updateSWTwithJoda(dTo, dt);
        		}
      		});

		Composite searchBoxes = new Composite(shell, SWT.NONE);
		searchBoxes.setLayout(new RowLayout());


		Label lblSearchNbr = new Label(searchBoxes, SWT.HORIZONTAL | SWT.SHADOW_OUT);
		lblSearchNbr.setText("Search Base Number: ");

		final Text TextBaseNumber = new Text(searchBoxes, SWT.CENTER | SWT.FILL | SWT.BORDER);

		Button btnSearch = new Button (shell, SWT.PUSH);

		btnSearch.setText("Search");
		btnSearch.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		btnSearch.addSelectionListener (new SelectionAdapter () {
        		public void widgetSelected (SelectionEvent e) {
				//validateControls();	//To be complated
				//Do your damn thing here!
				dateFrom = DateUtils.makeJodaFromSWT(dFrom);
				dateTo = DateUtils.makeJodaFromSWT(dTo);
				searchStr = TextBaseNumber.getText();
          			shell.close();
        		}
      		});

	}

	private org.joda.time.DateTime showCalPopup(Shell s) {
		final Shell dialog = new Shell (s, SWT.DIALOG_TRIM);

		final org.joda.time.DateTime dateFromSelection = new org.joda.time.DateTime();

		dialog.setLayout (new GridLayout (1, false));

		final DateTime date = new DateTime (dialog, SWT.DATE | SWT.MEDIUM);
		final DateTime calendar = new DateTime (dialog, SWT.CALENDAR | SWT.BORDER);
		Button ok = new Button (dialog, SWT.PUSH);

		calendar.addSelectionListener (new SelectionAdapter () {
			public void widgetSelected (SelectionEvent e) {
				DateUtils.bindDate(date, calendar);
			}
		});
		date.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		date.addSelectionListener (new SelectionAdapter () {
			public void widgetSelected (SelectionEvent e) {
				DateUtils.bindDate(calendar, date);
			}
		});

		ok.setText ("OK");
		ok.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		ok.addSelectionListener (new SelectionAdapter () {
			public void widgetSelected (SelectionEvent e) {
				dateFromSelection.withDate(date.getYear(), date.getMonth(), date.getDay());
				dialog.close ();
			}
		});

		dialog.setDefaultButton (ok);
		dialog.pack ();
		dialog.open ();

		return dateFromSelection;

	}

}