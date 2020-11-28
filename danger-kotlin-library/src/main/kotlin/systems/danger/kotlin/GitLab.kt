@file:UseSerializers(DateSerializer::class)

package systems.danger.kotlin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import systems.danger.kotlin.serializers.DateSerializer
import java.util.*

@Serializable
data class GitLab(
    @SerialName("mr")
    val mergeRequest: GitLabMergeRequest,
    val metadata: GitLabMetadata
)

@Serializable
data class GitLabDiffRefs(
    @SerialName("base_sha")
    val baseSha: String,
    @SerialName("head_sha")
    val headSha: String,
    @SerialName("start_sha")
    val startSha: String
)

@Serializable
data class GitLabUserMergeData(
    @SerialName("can_merge")
    val canMerge: Boolean
)

@Serializable
data class GitLabMergeRequest(
    @SerialName("allow_collaboration")
    val allowCollaboration: Boolean = false,
    @SerialName("allow_maintainer_to_push")
    val allowMaintainerToPush: Boolean = false,
    @SerialName("approvals_before_merge")
    val approvalsBeforeMerge: Int? = 1,
    val assignee: GitLabUser?,
    val author: GitLabUser,
    @SerialName("changes_count")
    val changesCount: String,
    @SerialName("closed_at")
    val closedAt: Date? = null,
    @SerialName("closed_by")
    val closedBy: GitLabUser?,
    val description: String,
    @SerialName("diff_refs")
    val diffRefs: GitLabDiffRefs,
    val downvotes: Int,
    @SerialName("first_deployed_to_production_at")
    val firstDeployedToProductionAt: Date? = null,
    @SerialName("force_remove_source_branch")
    val forceRemoveSourceBranch: Boolean,
    val id: Int,
    val iid: Int,
    @SerialName("latest_build_finished_at")

    val latestBuildFinishedAt: Date? = null,
    @SerialName("latest_build_started_at")

    val latestBuildStartedAt: Date? = null,
    val labels: List<String>,
    @SerialName("merge_commit_sha")
    val mergeCommitSha: String? = null,
    @SerialName("merged_at")
    val mergedAt: Date? = null,
    @SerialName("merged_by")
    val mergedBy: GitLabUser?,
    @SerialName("merge_when_pipeline_succeeds")
    val mergeOnPipelineSuccess: Boolean,
    val milestone: GitLabMilestone? = null,
    val pipeline: GitLabPipeline,
    @SerialName("project_id")
    val projectId: String,
    val sha: String,
    @SerialName("should_remove_source_branch")
    val shouldRemoveSourceBranch: Boolean? = null,
    @SerialName("source_branch")
    val sourceBranch: String,
    @SerialName("source_project_id")
    val sourceProjectId: String,
    val state: GitLabMergeRequestState,
    val subscribed: Boolean,
    @SerialName("target_branch")
    val targetBranch: String,
    @SerialName("target_project_id")
    val targetProjectId: String,
    val timeStats: GitLabMergeRequestTimeStats? = null,
    val title: String,
    val upvotes: Int,
    @SerialName("user")
    private val userMergeData: GitLabUserMergeData,
    @SerialName("user_notes_count")
    val userNotesCount: Int,
    @SerialName("web_url")
    val webUrl: String,
    @SerialName("work_in_progress")
    val workInProgress: Boolean
) {
    val canMerge: Boolean
        get() = this.userMergeData.canMerge
}

@Serializable
data class GitLabMergeRequestTimeStats(
    @SerialName("human_time_estimate")
    val humanTimeEstimate: Int?,
    @SerialName("human_time_spent")
    val humanTimeSpent: Int?,
    @SerialName("time_estimate")
    val timeEstimate: Int,
    @SerialName("total_time_spent")
    val totalTimeSpent: Int
)

@Serializable
data class GitLabMetadata(
    val pullRequestID: String,
    val repoSlug: String
)

@Serializable
enum class GitLabMergeRequestState {
    @SerialName("closed")
    CLOSED,

    @SerialName("locked")
    LOCKED,

    @SerialName("merged")
    MERGED,

    @SerialName("opened")
    OPENED
}

@Serializable
data class GitLabMilestone(
    @SerialName("created_at")
    val createdAt: Date,
    val description: String,
    @SerialName("due_date")
    val dueDate: Date,
    val id: Int,
    val iid: Int,
    @SerialName("project_id")
    val projectID: Int,
    @SerialName("start_date")
    val startDate: Date,
    val state: GitLabMilestoneState,
    val title: String,
    @SerialName("updated_at")
    val updatedAt: Date,
    @SerialName("web_url")
    val webUrl: String
)

@Serializable
enum class GitLabMilestoneState {
    @SerialName("active")
    ACTIVE,

    @SerialName("closed")
    CLOSED
}

@Serializable
data class GitLabPipeline(
    val id: Int,
    val ref: String,
    val sha: String,
    val status: GitLabPipelineStatus,
    @SerialName("web_url")
    val webUrl: String
)

@Serializable
enum class GitLabPipelineStatus {
    @SerialName("cancelled")
    CANCELLED,

    @SerialName("failed")
    FAILED,

    @SerialName("pending")
    PENDING,

    @SerialName("running")
    RUNNING,

    @SerialName("skipped")
    SKIPPED,

    @SerialName("success")
    SUCCESS
}

@Serializable
data class GitLabUser(
    @SerialName("avatar_url")
    val avatarUrl: String?,
    val id: Int,
    val name: String,
    val state: GitLabUserState,
    val username: String,
    @SerialName("web_url")
    val webUrl: String
)

@Serializable
enum class GitLabUserState {
    @SerialName("active")
    ACTIVE,

    @SerialName("blocked")
    BLOCKED
}