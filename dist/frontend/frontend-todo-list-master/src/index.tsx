import React from "react";
import ReactDOM from "react-dom";
import {Provider} from "react-redux";

import {MainPage} from "./pages/MainPage"
import {store} from "./store";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "./index.css";

ReactDOM.render(
    <Provider store={store}>
        <React.StrictMode>
            <MainPage.Container/>
        </React.StrictMode>
    </Provider>,
    document.getElementById("root")
);
