import { Model } from 'mongoose';
import { User, UserDocument } from '../auth/schema/user.schema';
export declare class MenteeService {
    private userModel;
    constructor(userModel: Model<UserDocument>);
    findAllMentees(): Promise<User[]>;
}
