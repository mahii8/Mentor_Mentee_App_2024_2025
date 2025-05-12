import { CreateTaskDto } from './dto/create-task.dto';
import { UpdateTaskDto } from './dto/update-task.dto';
import { TaskService } from './tasks.service';
import { Task } from './schemas/task.schema';
export declare class TaskController {
    private readonly taskService;
    constructor(taskService: TaskService);
    createTask(authHeader: string, dto: CreateTaskDto): Promise<Task>;
    findOne(id: string): Promise<Task>;
    getAssignedTasks(authHeader: string): Promise<Task[]>;
    update(id: string, dto: Partial<UpdateTaskDto>): Promise<Task>;
    remove(id: string): Promise<{
        deleted: boolean;
    }>;
    changeTaskCompletionStatus(id: string): Promise<Task>;
    fetchTasks(authHeader: string): Promise<Task[]>;
}
