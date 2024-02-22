package cz.vutbr.fit.gja.androidDatePicker2;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnDateSetListener {

  private TextView tvDisplayDate;
  private DatePicker dpResult;
  private Button btnChangeDate;

  private int year;
  private int month;
  private int day;

  static final int DATE_DIALOG_ID = 999;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setCurrentDateOnView();
    addListenerOnButton();

  }

  // display current date
  public void setCurrentDateOnView() {

    tvDisplayDate = (TextView) findViewById(R.id.tvDate);
    dpResult = (DatePicker) findViewById(R.id.dpResult);

    final Calendar c = Calendar.getInstance();
    year = c.get(Calendar.YEAR);
    month = c.get(Calendar.MONTH);
    day = c.get(Calendar.DAY_OF_MONTH);

    // set current date into textview
    tvDisplayDate.setText(new StringBuilder()
        // Month is 0 based, just add 1
        .append(month + 1).append("-").append(day).append("-")
        .append(year).append(" "));

    // set current date into datepicker
    dpResult.init(year, month, day, null);

  }

  public void addListenerOnButton() {

    btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

    btnChangeDate.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        Bundle b = new Bundle();
        b.putInt(DatePickerDialogFragment.YEAR, year);
        b.putInt(DatePickerDialogFragment.MONTH, month);
        b.putInt(DatePickerDialogFragment.DATE, day);
        DialogFragment picker = new DatePickerDialogFragment();
        picker.setArguments(b);
        picker.show(getSupportFragmentManager(), "fragment_date_picker");
      }

    });

  }

  @Override
  public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
  {
    this.year = year;
    this.month = monthOfYear;
    this.day = dayOfMonth;

    tvDisplayDate.setText(new StringBuilder()
        // Month is 0 based, just add 1
        .append(month + 1).append("-").append(day).append("-")
        .append(year).append(" "));
  }


  private DatePickerDialog.OnDateSetListener datePickerListener
      = new DatePickerDialog.OnDateSetListener() {

    // when dialog box is closed, below method will be called.
    public void onDateSet(DatePicker view, int selectedYear,
                          int selectedMonth, int selectedDay) {
      year = selectedYear;
      month = selectedMonth;
      day = selectedDay;

      // set selected date into textview
      tvDisplayDate.setText(new StringBuilder().append(month + 1)
          .append("-").append(day).append("-").append(year)
          .append(" "));

      // set selected date into datepicker also
      dpResult.init(year, month, day, null);

    }
  };

}
