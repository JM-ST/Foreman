package th.ac.ku.cs.sci.Foreman.Controller.ModelController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Model.Site;
import th.ac.ku.cs.sci.Foreman.Service.SiteService;

import java.util.Collection;

@Controller
public class SiteController {

    private final SiteService service ;

    @Autowired
    public SiteController(SiteService service) {
        this.service = service ;
    }

    public void createSite(String name) {
        Site site = new Site(name,Site.Status.PLANING);
        service.createSite(site);
    }

    public Collection<Site> getAll() {
        return service.getAllSite();
    }


}