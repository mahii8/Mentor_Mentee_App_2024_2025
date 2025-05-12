import { Document } from 'mongoose';
export type MentorDocument = Mentor & Document;
export declare class Mentor {
    name: string;
    email: string;
    password: string;
    role: string;
}
export declare const MentorSchema: import("mongoose").Schema<Mentor, import("mongoose").Model<Mentor, any, any, any, Document<unknown, any, Mentor, any> & Mentor & {
    _id: import("mongoose").Types.ObjectId;
} & {
    __v: number;
}, any>, {}, {}, {}, {}, import("mongoose").DefaultSchemaOptions, Mentor, Document<unknown, {}, import("mongoose").FlatRecord<Mentor>, {}> & import("mongoose").FlatRecord<Mentor> & {
    _id: import("mongoose").Types.ObjectId;
} & {
    __v: number;
}>;
