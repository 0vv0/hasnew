package home.olse.hasnew.controllers;

import home.olse.hasnew.services.keeper.KeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/save/")
public class SaveController {
    private KeeperService<String, String> keeper;

    @Autowired
    public SaveController(KeeperService<String, String> keeper) {
        this.keeper = keeper;
    }

    @RequestMapping(value = "{filename}/{version}", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String saveVersion(@PathVariable("filename") String clazz, @PathVariable("version") String version) {
        keeper.saveData(clazz, version);
        return "OK";
    }
}
