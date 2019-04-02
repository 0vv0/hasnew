package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DPDInfoService dpds;

    @RequestMapping("list")
    @ResponseBody
    public String get() {
        StringBuilder sb = new StringBuilder("<table id=\"versionsTable\" border = 1>");
        sb.append("<thead>");
        sb.append("<tr><td>Name</td><td>Version</td><td>Release Date</td><td>DPD</td></tr>");
        sb.append("<tr><td>")
                .append(appsLister.getPath()).append("</td><td></td><td></td><td>")
                .append(dpds.getPath()).append("</td></tr>");
        sb.append("</thead>");
        sb.append("<tbody>");
        for (VersionedApp app : appsLister.apps()) {
            sb
                    .append("<tr>")
                    .append("<td>").append(app.getName()).append("</td>")
                    .append("<td>").append(app.getVersion()).append("</td>")
                    .append("<td>").append(app.getDate()).append("</td>")
                    .append("<td>").append(dpds.getLastVersion(app)).append("</td>")
                    .append("</tr>");
        }
        sb.append("</tbody>").append("</table>");

        return sb.toString();
    }

    @RequestMapping("")
    @ResponseBody
    public String root() {
        String body = "OK. <a href=\"/list\">list</a>";

        return body + "<br><hr>" + getFileListTable();
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
        if (app != null) {
            String sb = "<tr>" +
                    "<td>" + app.getName() + "</td>" +
                    "<td>" + app.getVersion() + "</td>" +
                    "<td>" + app.getDate() + "</td>" +
                    "</tr>";
            return sb;
        } else {
            return "</tr>";
        }
    }

    private String getTableEntry(String trNode) {
        return "<table><tbody>" + trNode + "</tbody></table>";
    }

}
