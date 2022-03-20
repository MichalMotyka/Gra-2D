import {createPool} from "mysql2/promise";

export const pool = createPool({
    host: 'localhost',
    user: 'root',
    database: '2d_game',
    decimalNumbers: true,
    namedPlaceholders: true,
})
