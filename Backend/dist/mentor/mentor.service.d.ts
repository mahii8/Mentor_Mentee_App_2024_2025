import { Model } from 'mongoose';
import { User, UserDocument } from '../auth/schema/user.schema';
export declare class MentorService {
    private userModel;
    constructor(userModel: Model<UserDocument>);
    findAllMentors(): Promise<User[]>;
}
