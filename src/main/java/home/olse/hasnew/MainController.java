package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    private Apps appsLister;

    @RequestMapping("/list")
    @ResponseBody
    public String get() {
        StringBuilder sb = new StringBuilder("<table id=\"versionsTable\" border = 1>");
        sb.append("<tbody>");
        for (VersionedApp app : appsLister.getList()) {
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

    @RequestMapping("/")
    @ResponseBody
    public String root() {
        return "OK. <a href=\"/list\">list</a>";
    }
}
