import {slice, asyncActions} from "./reducer";
import Container from "./container";

const Actions = {
    ...slice.actions,
    ...asyncActions
};

const Reducer = slice.reducer;

export const MainPage = {
    Actions,
    Container,
    Reducer,
};