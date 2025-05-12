import { MentorshipRequestService } from './mentorship-request.service';
import { CreateMentorshipRequestDto } from './dto/mentorship-request.dto';
import { MentorshipRequest } from './schemas/mentorship-request.schema';
import { UpdateStatusDto } from './dto/update-status.dto';
export declare class MentorshipRequestController {
    private readonly mentorshipRequestService;
    constructor(mentorshipRequestService: MentorshipRequestService);
    create(dto: CreateMentorshipRequestDto, authHeader: string): Promise<MentorshipRequest>;
    findAll(authHeader: string): Promise<MentorshipRequest[]>;
    findAllMentees(authHeader: string): Promise<MentorshipRequest[]>;
    findOne(id: string): Promise<MentorshipRequest | null>;
    update(id: string, dto: Partial<CreateMentorshipRequestDto>): Promise<MentorshipRequest | null>;
    remove(id: string): Promise<{
        deleted: boolean;
    }>;
    setRequestStatus(id: string, updateStatusDto: UpdateStatusDto): Promise<MentorshipRequest>;
}
