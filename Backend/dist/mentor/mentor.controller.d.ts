import { MentorService } from './mentor.service';
export declare class MentorController {
    private readonly mentorService;
    constructor(mentorService: MentorService);
    getAllMentors(): Promise<import("../auth/schema/user.schema").User[]>;
}
