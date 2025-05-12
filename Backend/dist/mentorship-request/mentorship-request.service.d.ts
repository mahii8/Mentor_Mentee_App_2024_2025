import { Model } from 'mongoose';
import { MentorshipRequest, MentorshipRequestDocument } from './schemas/mentorship-request.schema';
import { CreateMentorshipRequestDto } from './dto/mentorship-request.dto';
import { Status } from './enums/status.enum';
import { TokenExtractionService } from '../auth/token.service';
export declare class MentorshipRequestService {
    private readonly mentorshipRequestModel;
    private tokenService;
    constructor(mentorshipRequestModel: Model<MentorshipRequestDocument>, tokenService: TokenExtractionService);
    setRequestStatus(id: string, status: Status): Promise<MentorshipRequest>;
    createMentorshipRequest(dto: CreateMentorshipRequestDto, token: string): Promise<MentorshipRequest>;
    getAllRequests(token: string): Promise<MentorshipRequest[]>;
    getAllRequestsSendByMentees(token: string): Promise<MentorshipRequest[]>;
    getRequestById(id: string): Promise<MentorshipRequest | null>;
    deleteRequest(id: string): Promise<{
        deleted: boolean;
    }>;
    updateRequest(id: string, dto: Partial<CreateMentorshipRequestDto>): Promise<MentorshipRequest | null>;
}
