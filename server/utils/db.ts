import {createPool} from "mysql2/promise";

export const pool = createPool({
    host: 'localhost',
    user: 'root',
    database: '2d_game',
    decimalNumbers: true,
    namedPlaceholders: true,
    // host: 'serwer2216476.home.pl',
    // user: '35854268_2dgame',
    // database: '35854268_2dgame',
    // password: 'Trudnehaslo12@',
    // decimalNumbers: true,
    // namedPlaceholders: true,
})
