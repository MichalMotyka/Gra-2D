import { Router } from "express";
import {UserRecord} from "../records/user.record";

export const usersRouter = Router();

usersRouter
    .post('/login', async (req, res) => {
        const { name } = req.body;

        if(!name) {
            res.status(400).json({message: "Nie ma takiego imienia użytkownika."})
        } else {
            const user = await UserRecord.getOne(name);
            res.status(200).json({name: name, id: user.id, points: user.points})
        }
    })
    .post('/register', async (req, res) => {
        const { name } = req.body;

        if(await UserRecord.isNameTaken(name)) {
            res.status(400).json({message: "Imię jest zajęte"})
        } else {
            const user = new UserRecord(name);

            await user.insert();

            res
                .status(201)
                .json({
                    id: user.id,
                });
        }
    })
    .patch('/update-points', (req, res) => {

    });
