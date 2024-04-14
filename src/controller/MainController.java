package controller;

import view.IView;

public class MainController {
    private IView view;
    public MainController(IView view) {
        this.view = view;
    }
    public IView getView() {
        return view;
    }
}
