import {XMLParser} from "fast-xml-parser";

export function handleAxiosError(error) {
    const parser = new XMLParser();
    if (!error.response && error.code === "ERR_NETWORK") {
        return {
            code: 'ERR_CONNECTION_REFUSED',
            message: 'Сервер недоступен',
            time: Date.now()
        };
    } else {
        if (typeof (error.response.data) == 'string') {
            return parser.parse(error.response.data).error
        }
        return error.response.data
    }
}