package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private Apps appsLister;
    @Autowired
    private DPDInfoService dpds;
    @Autowired
    private Config config;

    @RequestMapping(value = "list")
    @ResponseBody
    public String get() {
        StringBuilder sb = new StringBuilder("<table id=\"versionsTable\" border = 1>");
        sb.append("<thead>");
        sb.append("<tr><td>Name</td><td>Version</td><td>Release Date</td><td>DPD</td></tr>");
        sb.append("<tr><td>")
                .append(config.getAppsPath()).append("</td><td></td><td></td><td>")
                .append(config.getDpdPath()).append("</td></tr>");
        sb.append("</thead>");
        sb.append("<tbody>");
        for (VersionedApp app : appsLister.apps(config.getAppsPath())) {
            sb
                    .append(getTREntry(app));
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

    @RequestMapping(value = "{fileName}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getFile(@PathVariable String fileName) {
        VersionedApp app = appsLister.getByFileName(config.getAppsPath(), fileName);
        String s = getTableEntry(getTREntry(app));


        return "<html><body>" + s + "</body></html>";
    }

    private String getFileListTable() {
        List<String> fileNames = appsLister.filesNames(config.getAppsPath());
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
//            System.out.println(app.URL());
            return "<tr>" +
                    "<td><a href=\"" + app.URL() + "\" target=_blank>" + app.getName() + "</a></td>" +
                    "<td>" + app.getVersion() + "</td>" +
                    "<td>" + app.getDate() + "</td>" +
                    "<td>" + dpds.getLastVersion(config.getDpdPath(), app) + "</td>" +
                    "</tr>";
        } else {
            return "</tr>";
        }
    }

    private String getTableEntry(String trNode) {
        return "<table border = 1><tbody>" + trNode + "</tbody></table>";
    }
}
