import { Module } from "./module";
import { Rating } from "./rating";

export interface Course {
    id: string;
    title: string;
    course_duration: string;
    image_cape: string;
    description: string;
    price: number;
    professor_id: string;
}
