package home.olse.hasnew;

import org.springframework.stereotype.Component;

@Component
public class TableUtils {
    public static String getTREntry(String s) {
        return s == null ? "<tr/>" : "<tr>" + s + "</tr>";
    }

    public static String getTREntry(String... ss){
        if(ss==null){return "<tr/>";}
        StringBuffer answer = new StringBuffer("<tr>");
        for (String s : ss) {
            answer.append(getTDEntry(s));
        }
        return answer.append("</tr>").toString();
    }

    public static String getTDEntry(String s) {
        return s == null ? "<td/>" : "<td>" + s + "</td>";
    }

    public static String getTableEntry(String tBody) {
        return "<table border = 1><tbody>" + tBody + "</tbody></table>";
    }
}
