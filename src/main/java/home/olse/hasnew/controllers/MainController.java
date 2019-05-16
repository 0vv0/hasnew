package home.olse.hasnew.controllers;

import home.olse.hasnew.services.AppsService;
import home.olse.hasnew.services.DPDInfoService;
import home.olse.hasnew.services.VersionSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private AppsService appsLister;
    @Autowired
    private DPDInfoService dpds;
    @Autowired
    private VersionSaver keeper;
    @Autowired
    private Logger logger;

    @RequestMapping(value = "list")
    public String get(Model model) {
        model.addAttribute("dpds", dpds);
        model.addAttribute("all", appsLister.getList());
        model.addAttribute("keeper", keeper);

        return "all";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String root(Model model) {
        model.addAttribute("all", "/list");
        model.addAttribute("names", appsLister.names());
        return "root";
    }

    @RequestMapping(value = "all/{name}/save/{version}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getFile(@PathVariable String name, @PathVariable String version) {
        String s = keeper.saveVersion(name, version);
        return "<html><body>" + s + "</body></html>";
    }

    // Total control - setup a model and return the view name yourself. Or
    // consider subclassing ExceptionHandlerExceptionResolver (see below).
    @ExceptionHandler(Exception.class)
    public String handleError() {
//        model.addAttribute("exception", ex);
//        model.addAttribute("request", req);
        return "error";
    }

}
