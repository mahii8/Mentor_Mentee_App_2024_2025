"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.MenteeModule = void 0;
const common_1 = require("@nestjs/common");
const mentee_service_1 = require("./mentee.service");
const mentee_controller_1 = require("./mentee.controller");
const mongoose_1 = require("@nestjs/mongoose");
const user_schema_1 = require("../auth/schema/user.schema");
let MenteeModule = class MenteeModule {
};
exports.MenteeModule = MenteeModule;
exports.MenteeModule = MenteeModule = __decorate([
    (0, common_1.Module)({
        imports: [
            mongoose_1.MongooseModule.forFeature([{ name: user_schema_1.User.name, schema: user_schema_1.UserSchema }]),
        ],
        controllers: [mentee_controller_1.MenteeController],
        providers: [mentee_service_1.MenteeService],
    })
], MenteeModule);
//# sourceMappingURL=mentee.module.js.map