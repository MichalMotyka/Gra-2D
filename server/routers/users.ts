import { Router } from "express";
import {UserRecord} from "../records/user.record";

export const usersRouter = Router();

usersRouter
    .post('/login', async (req, res) => {

    })
    .post('/register', async (req, res) => {
        const { name } = req.body;

        const user = new UserRecord(name);

        await user.insert();

        res
            .status(201)
            .json({
                id: user.id,
            });
    });
