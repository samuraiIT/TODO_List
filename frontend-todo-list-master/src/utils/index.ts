import { ActionCreatorsMapObject, bindActionCreators } from "@reduxjs/toolkit";
import {useDispatch} from "react-redux";

export function useActions<T extends ActionCreatorsMapObject>(actions: T) {
    const dispatch = useDispatch();
    return bindActionCreators(actions, dispatch);
}