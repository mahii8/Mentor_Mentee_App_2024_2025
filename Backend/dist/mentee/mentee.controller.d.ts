import { MenteeService } from './mentee.service';
export declare class MenteeController {
    private readonly menteeService;
    constructor(menteeService: MenteeService);
    getAllMentees(): Promise<import("../auth/schema/user.schema").User[]>;
}
