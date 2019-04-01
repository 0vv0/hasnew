package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private Apps appsLister;

    @RequestMapping("list")
    @ResponseBody
    public String get() {
        StringBuilder sb = new StringBuilder("<table id=\"versionsTable\" border = 1>");
        sb.append("<tbody>");
        for (VersionedApp app : appsLister.apps()) {
            sb
                    .append("<tr>")
                    .append("<td>").append(app.getName())
                    .append("<td>").append(app.getVersion())
                    .append("<td>").append(app.getDate())
                    .append("</tr>");
        }
        sb.append("</tbody>").append("</table>");

        return sb.toString();
    }

    @RequestMapping("")
    @ResponseBody
    public String root() {
        String body = "OK. <a href=\"/list\">list</a>";

        return body + "<br>" + getFileListTable();
    }

    @RequestMapping(value = "{fileName}")
    @ResponseBody
    public String getFile(@PathVariable String fileName) {
        VersionedApp app = appsLister.getByFileName(fileName);
        String s = getTableEntry(getTREntry(app));


        return s;
    }

    private String getFileListTable() {
        List<String> fileNames = appsLister.files();
        StringBuilder sb = new StringBuilder("<table><tbody>");
        fileNames.forEach(x ->
                sb
                        .append("<tr>")
                        .append("<td>")
                        .append("<a href=\"/").append(x).append("\">").append(x).append("</a>")
                        .append("</td>")
                        .append("</tr>")
        );

        return sb.append("</tbody></table>").toString();

    }

    private String getTREntry(VersionedApp app) {
        assert app != null;
        String sb = "<tr>" +
                "<td>" + app.getName() + "</td>" +
                "<td>" + app.getVersion() + "</td>" +
                "<td>" + app.getDate() + "</td>" +
                "</tr>";
        return sb;
    }

    private String getTableEntry(String trNode) {
        return "<table><tbody>" + trNode + "</tbody></table>";
    }

}
