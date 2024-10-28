import React, {FC, useState} from "react";

type Props = {
    createNewItem: Function
}

const NewItem: FC<Props> = ({createNewItem}) => {
    const handleCreateNew = () => {
        createNewItem(text)
        onChange("")
    }

    const [text, onChange] = useState("");
    return (
        <div className="row justify-content-center">
            <div className="col-4 mt-5">
                <h2 className="h2">&nbsp;TODO application</h2>
                <div className="input-group mb-3">
                    <input type="text"
                           placeholder="TODO"
                           value={text}
                           className="form-control"
                           onChange={(e) => onChange(e.target.value)}/>
                    <button type="button" className="btn btn-outline-info" onClick={handleCreateNew}>
                        <i className="bi bi-plus-circle"/>
                    </button>
                </div>
            </div>
        </div>
    );
}

export default NewItem;