package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.board.Comment;
import study.myshop.domain.board.Notice;
import study.myshop.domain.board.Post;
import study.myshop.domain.board.Reply;
import study.myshop.domain.member.Admin;
import study.myshop.domain.member.Member;
import study.myshop.repository.board.CommentRepository;
import study.myshop.repository.board.NoticeRepository;
import study.myshop.repository.board.PostRepository;
import study.myshop.repository.board.ReplyRepository;
import study.myshop.repository.member.AdminRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.member.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;
    private final PostRepository postRepository;
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long notice(Long adminId, String title, String content) {
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow();
        Notice notice = Notice.createNotice(findAdmin, title, content);
        noticeRepository.save(notice);
        return notice.getId();
    }

    @Transactional
    public Long post(Long memberId, String title, String content) {
        Member findMember = memberRepository.findById(memberId);
        Post post = Post.createPost(title, content, findMember);
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public Long comment(Long memberId, Long postId, String content) {
        Member findMember = memberRepository.findById(memberId);
        Post findPost = postRepository.findById(postId).orElseThrow();
        Comment comment = Comment.createComment(findMember, findPost, content);
        commentRepository.save(comment);
        return comment.getId();
    }
    
    // 대댓글
    @Transactional
    public void reply(Long memberId, Long commentId, String content) {
        Member findMember = memberRepository.findById(memberId);
        Comment parent = commentRepository.findById(commentId).orElseThrow();

        Comment child = Comment.createComment(findMember, parent.getPost(), content);
        parent.reply(child);
    }

    @Transactional
    public void updateNotice(Long adminId, Long noticeId, String title, String content) {
        Notice findNotice = noticeRepository.findById(noticeId).orElseThrow();
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow();
        
        // 다른 admin도 수정 가능
        findNotice.update(findAdmin, title, content);
    }

    @Transactional
    public void updatePost(Long memberId, Long postId, String title, String content) {
        Post findPost = postRepository.findById(postId).orElseThrow();

        if (findPost.getMember().getId() != memberId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        findPost.update(title, content);
    }

    @Transactional
    public void updateComment(Long memberId, Long commentId, String content) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow();

        if (findComment.getMember().getId() != memberId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        findComment.update(content);
    }

    @Transactional
    public void removeNotice(Long adminId, Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).orElseThrow();
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow();

        findNotice.remove();
    }

    @Transactional
    public void removePost(Long memberId, Long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow();

        if (findPost.getMember().getId() != memberId) {
            throw new RuntimeException("올바르지 않은 접근");
        }
        
        findPost.remove();
    }

    @Transactional
    public void removeComment(Long memberId, Long commentId) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow();

        if (findComment.getMember().getId() != memberId) {
            throw new RuntimeException("올바르지 않은 접근");
        }

        findComment.remove();
    }


    public List<Notice> findNotices() {
        return noticeRepository.findAll();
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public List<Comment> findComments(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

}
