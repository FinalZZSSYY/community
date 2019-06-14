package life.majiang.community.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}
