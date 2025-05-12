import { Role } from '../roles/role.enum';
import { Skill } from '../skills/skills.enums';
export declare class SignupDto {
    readonly name: string;
    readonly email: string;
    readonly password: string;
    readonly role: Role;
    readonly skill: Skill;
}
