﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Data;

namespace MangaTracker.Helper.Converter
{
    public class DateTimeToAgoConverter : IMultiValueConverter
    {
        public object Convert(object[] values, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            if (values.Count() == 2) {
                DateTime d1=(DateTime)(values[0]);
                DateTime d2=(DateTime)(values[1]);
                var ts = new TimeSpan(d2.Ticks - d1.Ticks);
                double delta = Math.Abs(ts.TotalSeconds);
                if (delta < 60)
                    return "less than a minute ago";
                if (delta < 120)
                    return "a minute ago";
                if (delta < 2700) // 45 * 60
                    return ts.Minutes + " minutes ago";
                if (delta < 5400) // 90 * 60
                    return "an hour ago";
                if (delta < 86400) // 24 * 60 * 60
                    return ts.Hours + " hours ago";
                if (delta < 172800) // 48 * 60 * 60
                    return "yesterday";
                if (delta < 2592000) // 30 * 24 * 60 * 60
                    return ts.Days + " days ago";
                if (delta < 31104000){
                // 12 * 30 * 24 * 60 * 60
                    int months = System.Convert.ToInt32(Math.Floor((double)ts.Days / 30));
                    return months <= 1 ? "one month ago" : months + " months ago";
                }
                int years = System.Convert.ToInt32(Math.Floor((double)ts.Days / 365));
                return years <= 1 ? "one year ago" : years + " years ago";
            }
            throw new NotSupportedException("Should use multibinding to compare dates");
        }

        public object[] ConvertBack(object value, Type[] targetTypes, object parameter, System.Globalization.CultureInfo culture)
        {
            return Array.ConvertAll<Type, Object>(targetTypes, t => value);
        }
    }
}
