export type Item = {
    uid: string
    text: string
}

export type FieldErrorType = {
    field: string;
    error: string
};

export type ErrorResponse = {
    rejectValue: { errors: Array<string>; fieldsErrors?: Array<FieldErrorType> };
};