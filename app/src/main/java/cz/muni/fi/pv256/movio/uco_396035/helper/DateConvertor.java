package cz.muni.fi.pv256.movio.uco_396035.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fabian on 21.10.2017.
 */

public class DateConvertor {

    public static String longDateToString(long longDate) {
        Date date=new Date(longDate);
        SimpleDateFormat df2 = new SimpleDateFormat("dd.MMM.yyyy");
        return df2.format(date);
    }
}
