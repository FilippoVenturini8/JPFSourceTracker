import controller.Controller;
import controller.ControllerImpl;
import gov.nasa.jpf.vm.Verify;
import model.Model;
import model.ModelImpl;
import view.ConsoleView;
import view.View;
import utils.SetupInfo;

import static model.MasterThread.N_WORKERS;

public class TestSourceTracker {

    public static void main(String[] args){
        final SetupInfo setUpInfo = new SetupInfo("D:\\Progetti", 10, 10, 150);
        final Model model = new ModelImpl();
        final View view = new ConsoleView();
        final Controller controller = new ControllerImpl(model, view);

        model.addObserver(view);
        controller.start(setUpInfo, N_WORKERS);
    }

}