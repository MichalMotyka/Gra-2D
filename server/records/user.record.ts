import {pool} from "../utils/db";
import { v4 as uuid} from "uuid";

export class UserRecord {

    private readonly name: string;
    public readonly id: string;
    private readonly points: number;

    constructor(name: string) {

        this.name = name;
        this.id = uuid();
        this.points = 0;
    }

    async insert(): Promise<string> {

        await pool.execute("INSERT INTO `users`(`id`, `name`, `points`) VALUES (:id, :name, :points)", {
            id: this.id,
            name: this.name,
            points: this.points
        });

        return (this.id as string);
    }
}
