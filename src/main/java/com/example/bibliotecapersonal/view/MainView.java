package com.example.bibliotecapersonal.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("¡Haz clic aquí!");
        button.addClickListener(e -> button.setText("¡Hola Vaadin!"));
        add(button);
    }
}

