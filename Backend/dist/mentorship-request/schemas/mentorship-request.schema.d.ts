import { Document, Types } from 'mongoose';
import { Status } from '../enums/status.enum';
export type MentorshipRequestDocument = MentorshipRequest & Document;
export declare class MentorshipRequest {
    startDate: Date;
    endDate: Date;
    mentorshipTopic: string;
    additionalNotes: string;
    menteeId: Types.ObjectId;
    mentorId: Types.ObjectId;
    status: Status;
}
export declare const MentorshipRequestSchema: import("mongoose").Schema<MentorshipRequest, import("mongoose").Model<MentorshipRequest, any, any, any, Document<unknown, any, MentorshipRequest, any> & MentorshipRequest & {
    _id: Types.ObjectId;
} & {
    __v: number;
}, any>, {}, {}, {}, {}, import("mongoose").DefaultSchemaOptions, MentorshipRequest, Document<unknown, {}, import("mongoose").FlatRecord<MentorshipRequest>, {}> & import("mongoose").FlatRecord<MentorshipRequest> & {
    _id: Types.ObjectId;
} & {
    __v: number;
}>;
