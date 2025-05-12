import { Model } from 'mongoose';
import { Task, TaskDocument } from './schemas/task.schema';
import { CreateTaskDto } from './dto/create-task.dto';
import { UpdateTaskDto } from './dto/update-task.dto';
import { TokenExtractionService } from '../auth/token.service';
export declare class TaskService {
    private readonly taskModel;
    private tokenService;
    constructor(taskModel: Model<TaskDocument>, tokenService: TokenExtractionService);
    createTask(dto: CreateTaskDto, token: string): Promise<Task>;
    findOne(id: string): Promise<Task>;
    getAssignedTasks(token: string): Promise<Task[]>;
    update(id: string, dto: Partial<UpdateTaskDto>): Promise<Task>;
    remove(id: string): Promise<{
        deleted: boolean;
    }>;
    changeTaskCompletionStatus(id: string): Promise<Task>;
    fetchTasks(token: string): Promise<Task[]>;
}
