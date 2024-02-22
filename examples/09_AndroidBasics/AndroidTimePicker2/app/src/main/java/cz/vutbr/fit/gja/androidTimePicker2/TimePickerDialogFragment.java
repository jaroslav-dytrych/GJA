package cz.vutbr.fit.gja.androidTimePicker2;

import android.annotation.TargetApi;
import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;


public class TimePickerDialogFragment extends DialogFragment
{
  public static final String HOUR = "Hour";
  public static final String MINUTE = "Minute";

  private OnTimeSetListener tListener;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.tListener = (OnTimeSetListener) context;
  }

  @Override
  public void onDetach() {
    this.tListener = null;
    super.onDetach();
  }

  @TargetApi(11)
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    Bundle b = getArguments();
    int h = b.getInt(HOUR);
    int m = b.getInt(MINUTE);

    final TimePickerDialog picker = new TimePickerDialog(getActivity(), tListener, h, m,
      DateFormat.is24HourFormat(getActivity()));

    return picker;
  }

  private static boolean hasJellyBeanAndAbove()
  {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
  }

  private OnTimeSetListener getConstructorListener() {
    return hasJellyBeanAndAbove() ? null : tListener;
  }
}