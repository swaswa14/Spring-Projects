import {NextApiRequest, NextApiResponse} from "next";

export interface Person {
    id: number;
    firstName: string;
    lastName: string;
    gender: "Female" | "Male" | "Other"
}
export default async function handler(
    req: NextApiRequest,
    res: NextApiResponse<Person[]>
) {
    try {
        const response = await fetch("http://localhost:8080/api/v1/person/all");
        const data = await response.json();
        res.status(200).json(data);
    }catch (error){
        console.error(error);
        res.status(500);
    }
}
