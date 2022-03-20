import {pool} from "../utils/db";
import { v4 as uuid} from "uuid";
import {FieldPacket} from "mysql2";

export class UserRecord {

    private readonly name: string;
    public readonly id: string;
    public readonly points: number;

    constructor(name: string) {

        this.name = name;
        this.id = uuid();
        this.points = 0;
    }

    static async getOne(name: string): Promise<UserRecord | null> {
        const [results] = await pool.execute("SELECT * FROM `users` WHERE `name` = :name", {
            name
        }) as [UserRecord[], FieldPacket[]];

        return results.length === 0 ? null : results[0];
    }

    async insert(): Promise<string> {

        await pool.execute("INSERT INTO `users`(`id`, `name`, `points`) VALUES (:id, :name, :points)", {
            id: this.id,
            name: this.name,
            points: this.points
        });

        return (this.id as string);
    }

    static async isNameTaken(name: string): Promise<boolean> {
        const [results] = await pool.execute("SELECT * FROM `users` WHERE `name` = :name", {
            name
        }) as [UserRecord[], FieldPacket[]];

        return results.length > 0;
    }
}
