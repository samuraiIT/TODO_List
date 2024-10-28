import {MainPage} from "../pages/MainPage"
import {configureStore} from "@reduxjs/toolkit";

const rootReducer = MainPage.Reducer

export const store = configureStore({
   reducer: rootReducer
})

export type RootState = ReturnType<typeof rootReducer>;