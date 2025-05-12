"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.TaskService = void 0;
const common_1 = require("@nestjs/common");
const mongoose_1 = require("@nestjs/mongoose");
const mongoose_2 = require("mongoose");
const task_schema_1 = require("./schemas/task.schema");
const token_service_1 = require("../auth/token.service");
let TaskService = class TaskService {
    constructor(taskModel, tokenService) {
        this.taskModel = taskModel;
        this.tokenService = tokenService;
    }
    async createTask(dto, token) {
        const mentorId = this.tokenService.extractUserId(token);
        const task = new this.taskModel({
            taskTitle: dto.taskTitle,
            description: dto.description,
            dueDate: dto.dueDate,
            priority: dto.priority,
            menteeId: new mongoose_2.Types.ObjectId(dto.menteeId),
            mentorId: new mongoose_2.Types.ObjectId(mentorId),
        });
        return await task.save();
    }
    async findOne(id) {
        const task = await this.taskModel.findById(id).exec();
        if (!task) {
            throw new common_1.NotFoundException(`Task with ID ${id} not found`);
        }
        return task;
    }
    async getAssignedTasks(token) {
        const userId = this.tokenService.extractUserId(token);
        const mentorObjectId = new mongoose_2.Types.ObjectId(userId);
        return await this.taskModel.find({ mentorId: mentorObjectId }).exec();
    }
    async update(id, dto) {
        const updated = await this.taskModel
            .findByIdAndUpdate(id, dto, {
            new: true,
        })
            .exec();
        if (!updated) {
            throw new common_1.NotFoundException(`Task with ID ${id} not found`);
        }
        return updated;
    }
    async remove(id) {
        const result = await this.taskModel.deleteOne({ _id: id }).exec();
        return { deleted: result.deletedCount > 0 };
    }
    async changeTaskCompletionStatus(id) {
        const task = await this.taskModel.findById(id);
        if (!task) {
            throw new Error('Task not found');
        }
        task.isCompleted = !task.isCompleted;
        return task.save();
    }
    async fetchTasks(token) {
        const userId = this.tokenService.extractUserId(token);
        const menteeObjectId = new mongoose_2.Types.ObjectId(userId);
        return await this.taskModel.find({ menteeId: menteeObjectId }).exec();
    }
};
exports.TaskService = TaskService;
exports.TaskService = TaskService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, mongoose_1.InjectModel)(task_schema_1.Task.name)),
    __metadata("design:paramtypes", [mongoose_2.Model,
        token_service_1.TokenExtractionService])
], TaskService);
//# sourceMappingURL=tasks.service.js.map