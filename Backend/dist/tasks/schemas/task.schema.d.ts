import { Document, Types } from 'mongoose';
export type TaskDocument = Task & Document;
export declare class Task {
    taskTitle: string;
    description: string;
    dueDate: Date;
    priority: string;
    mentorId: Types.ObjectId;
    menteeId: Types.ObjectId;
    isCompleted: boolean;
}
export declare const TaskSchema: import("mongoose").Schema<Task, import("mongoose").Model<Task, any, any, any, Document<unknown, any, Task, any> & Task & {
    _id: Types.ObjectId;
} & {
    __v: number;
}, any>, {}, {}, {}, {}, import("mongoose").DefaultSchemaOptions, Task, Document<unknown, {}, import("mongoose").FlatRecord<Task>, {}> & import("mongoose").FlatRecord<Task> & {
    _id: Types.ObjectId;
} & {
    __v: number;
}>;
