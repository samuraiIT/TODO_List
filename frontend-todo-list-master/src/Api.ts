import {Item} from "./pages/MainPage/types";

const baseURL = process.env.REACT_APP_BACKEND_IP;

export const Api = {
    async loadItems() {
        return await fetch(`${baseURL}/api/v1/public/items`)
    },
    async addNewItem(body: Item) {
        return await fetch(
            `${baseURL}/api/v1/public/items`,
            {
                method: "POST",
                body: JSON.stringify(body),
                headers: {
                    "Content-Type": "application/json"
                }
            })
    },
    async removeItem(uid: string) {
        return await fetch(
            `${baseURL}/api/v1/public/items/${uid}`,
            {
                method: "DELETE"
            })
    }
}